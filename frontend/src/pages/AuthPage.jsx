import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../AuthContext'
import * as api from '../api'

export default function AuthPage() {
  const [userType, setUserType] = useState('joueur')   // 'joueur' | 'moderateur'
  const [mode, setMode]         = useState('login')     // 'login' | 'register'
  const [form, setForm]         = useState({})
  const [error, setError]       = useState('')
  const [loading, setLoading]   = useState(false)
  const { signIn } = useAuth()
  const navigate = useNavigate()

  function set(field) {
    return (e) => setForm(f => ({ ...f, [field]: e.target.value }))
  }

  async function handleSubmit(e) {
    e.preventDefault()
    setError('')
    setLoading(true)
    try {
      if (mode === 'login') {
        const loginFn = userType === 'joueur' ? api.loginJoueur : api.loginModerateur
        const data = await loginFn(form.pseudo, form.motDePasse)
        let userId = null
        if (data.role === 'ROLE_JOUEUR') {
          const me = await api.getMe(data.token)
          userId = me.id
        }
        signIn({ token: data.token, role: data.role, pseudo: data.pseudo, userId })
        navigate('/')
      } else {
        if (userType === 'joueur') {
          await api.registerJoueur({
            pseudo: form.pseudo,
            motDePasse: form.motDePasse,
            email: form.email,
            dateDeNaissance: form.dateDeNaissance,
          })
        } else {
          await api.registerModerateur({
            pseudo: form.pseudo,
            motDePasse: form.motDePasse,
            email: form.email,
            numeroDeTelephone: form.numeroDeTelephone,
          })
        }
        setMode('login')
        setForm({})
        setError('Inscription réussie ! Connectez-vous.')
      }
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="auth-page">
      <div className="auth-card card">
        <h2>🎮 Avis de Jeux</h2>

        {/* Joueur / Modérateur */}
        <div className="tabs">
          <button className={`tab-btn${userType === 'joueur' ? ' active' : ''}`}
            onClick={() => { setUserType('joueur'); setForm({}); setError('') }}>
            Joueur
          </button>
          <button className={`tab-btn${userType === 'moderateur' ? ' active' : ''}`}
            onClick={() => { setUserType('moderateur'); setForm({}); setError('') }}>
            Modérateur
          </button>
        </div>

        {/* Login / Inscription */}
        <div className="tabs">
          <button className={`tab-btn${mode === 'login' ? ' active' : ''}`}
            onClick={() => { setMode('login'); setForm({}); setError('') }}>
            Connexion
          </button>
          <button className={`tab-btn${mode === 'register' ? ' active' : ''}`}
            onClick={() => { setMode('register'); setForm({}); setError('') }}>
            Inscription
          </button>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Pseudo</label>
            <input required value={form.pseudo || ''} onChange={set('pseudo')} placeholder="monpseudo" />
          </div>

          {mode === 'register' && (
            <div className="form-group">
              <label>Email</label>
              <input type="email" required value={form.email || ''} onChange={set('email')} />
            </div>
          )}

          <div className="form-group">
            <label>Mot de passe</label>
            <input type="password" required value={form.motDePasse || ''} onChange={set('motDePasse')} />
          </div>

          {mode === 'register' && userType === 'joueur' && (
            <div className="form-group">
              <label>Date de naissance</label>
              <input type="date" required value={form.dateDeNaissance || ''} onChange={set('dateDeNaissance')} />
            </div>
          )}

          {mode === 'register' && userType === 'moderateur' && (
            <div className="form-group">
              <label>Numéro de téléphone</label>
              <input value={form.numeroDeTelephone || ''} onChange={set('numeroDeTelephone')} placeholder="0612345678" />
            </div>
          )}

          {error && <p className="error">{error}</p>}

          <button type="submit" className="btn btn-primary" style={{ width: '100%', marginTop: 8 }} disabled={loading}>
            {loading ? 'Chargement…' : mode === 'login' ? 'Se connecter' : "S'inscrire"}
          </button>
        </form>
      </div>
    </div>
  )
}
