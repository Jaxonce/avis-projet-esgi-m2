import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../AuthContext'
import { getJeux, addJeu, getReferentiel } from '../api'
import AddGameModal from '../components/AddGameModal'

export default function JeuxPage() {
  const { auth } = useAuth()
  const navigate = useNavigate()
  const isModo = auth?.role === 'ROLE_MODERATEUR'

  const [jeux, setJeux]           = useState([])
  const [loading, setLoading]     = useState(true)
  const [showModal, setShowModal] = useState(false)
  const [referentiel, setRef]     = useState(null)

  useEffect(() => {
    getJeux()
      .then(setJeux)
      .catch(console.error)
      .finally(() => setLoading(false))
  }, [])

  async function handleAddGame(data) {
    await addJeu(data)
    const updated = await getJeux()
    setJeux(updated)
    setShowModal(false)
  }

  async function openModal() {
    if (!referentiel) {
      const ref = await getReferentiel()
      setRef(ref)
    }
    setShowModal(true)
  }

  if (loading) return <div className="spinner">Chargement…</div>

  return (
    <div className="container page">
      <div className="page-header">
        <h2>Catalogue de jeux <span style={{ color: 'var(--text-muted)', fontWeight: 400 }}>({jeux.length})</span></h2>
        {isModo && (
          <button className="btn btn-primary" onClick={openModal}>+ Ajouter un jeu</button>
        )}
      </div>

      {jeux.length === 0 ? (
        <p className="empty">Aucun jeu en base. {isModo ? 'Ajoutez-en un !' : ''}</p>
      ) : (
        <div className="games-grid">
          {jeux.map(jeu => (
            <div key={jeu.id} className="game-card" onClick={() => navigate(`/jeu/${jeu.id}`, { state: { jeu } })}>
              <h3>{jeu.nom}</h3>
              <div className="meta">
                {jeu.dateDeSortie && <span>{jeu.dateDeSortie}</span>}
                {jeu.prix != null && <span>{jeu.prix} €</span>}
              </div>
              <div style={{ display: 'flex', flexWrap: 'wrap', gap: 6, marginBottom: 10 }}>
                {jeu.editeur && <span className="tag">{jeu.editeur.nom}</span>}
                {jeu.genre   && <span className="tag genre">{jeu.genre.nom}</span>}
                {jeu.plateformes?.map(p => <span key={p.id} className="tag platform">{p.nom}</span>)}
              </div>
              {jeu.description && (
                <p style={{ fontSize: '.82rem', color: 'var(--text-muted)', lineHeight: 1.4 }}>
                  {jeu.description.length > 80 ? jeu.description.slice(0, 80) + '…' : jeu.description}
                </p>
              )}
            </div>
          ))}
        </div>
      )}

      {showModal && referentiel && (
        <AddGameModal
          referentiel={referentiel}
          onSubmit={handleAddGame}
          onClose={() => setShowModal(false)}
        />
      )}
    </div>
  )
}
