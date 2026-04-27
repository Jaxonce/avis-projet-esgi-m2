const BASE = 'http://localhost:8081'

function getToken() {
  return localStorage.getItem('token')
}

async function request(method, path, body, token) {
  const headers = { 'Content-Type': 'application/json' }
  const authToken = token ?? getToken()
  if (authToken) headers['Authorization'] = `Bearer ${authToken}`

  const res = await fetch(`${BASE}${path}`, {
    method,
    headers,
    body: body != null ? JSON.stringify(body) : undefined,
  })

  if (!res.ok) {
    const text = await res.text()
    throw new Error(text || `Erreur ${res.status}`)
  }

  const text = await res.text()
  if (!text) return null
  return JSON.parse(text)
}

// Auth
export const loginJoueur = (pseudo, motDePasse) =>
  request('POST', '/utilisateur/login', { pseudo, motDePasse })

export const loginModerateur = (pseudo, motDePasse) =>
  request('POST', '/moderateur/login', { pseudo, motDePasse })

export const registerJoueur = (data) =>
  request('POST', '/utilisateur/inscription', data)

export const registerModerateur = (data) =>
  request('POST', '/moderateur/inscription', data)

export const getMe = (token) =>
  request('GET', '/utilisateur/me', null, token)

export const logout = (role) =>
  request('POST', role === 'ROLE_MODERATEUR' ? '/moderateur/logout' : '/utilisateur/logout')

// Jeux
export const getJeux = () =>
  request('GET', '/utilisateur/jeu')

export const addJeu = (data) =>
  request('POST', '/moderateur/jeu', data)

// Avis
export const getAvis = (role) =>
  request('GET', role === 'ROLE_MODERATEUR' ? '/moderateur/avis' : '/utilisateur/avis')

export const writeAvis = (data) =>
  request('POST', '/utilisateur/avis', data)

export const deleteAvis = (id) =>
  request('DELETE', `/moderateur/avis/${id}`)

// Référentiel
export const getReferentiel = () =>
  request('GET', '/moderateur/referentiel')
