import { useAuth } from '../AuthContext'
import { logout } from '../api'

export default function Header() {
  const { auth, signOut } = useAuth()
  const isModo = auth?.role === 'ROLE_MODERATEUR'

  async function handleLogout() {
    try { await logout(auth.role) } catch (_) {}
    signOut()
  }

  return (
    <header>
      <div className="container inner">
        <h1>
          🎮 Avis de Jeux
          {auth && (
            <span className={`badge${isModo ? ' modo' : ''}`}>
              {isModo ? 'Modérateur' : 'Joueur'}
            </span>
          )}
        </h1>
        {auth && (
          <div style={{ display: 'flex', alignItems: 'center', gap: 12 }}>
            <span style={{ fontSize: '.9rem', color: 'var(--text-muted)' }}>
              {auth.pseudo}
            </span>
            <button className="btn-logout" onClick={handleLogout}>Déconnexion</button>
          </div>
        )}
      </div>
    </header>
  )
}
