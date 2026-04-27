import { useEffect, useState } from 'react'
import { useParams, useLocation, useNavigate } from 'react-router-dom'
import { useAuth } from '../AuthContext'
import { getAvis, writeAvis, deleteAvis, getJeux } from '../api'

export default function JeuDetailPage() {
  const { id } = useParams()
  const { state } = useLocation()
  const navigate = useNavigate()
  const { auth } = useAuth()
  const isModo = auth?.role === 'ROLE_MODERATEUR'

  const [jeu, setJeu]       = useState(state?.jeu ?? null)
  const [avis, setAvis]     = useState([])
  const [loading, setLoading] = useState(true)
  const [form, setForm]     = useState({ description: '', note: '' })
  const [error, setError]   = useState('')
  const [sending, setSending] = useState(false)

  const jeuId = Number(id)
  const jeuAvis = avis.filter(a => a.jeu?.id === jeuId)

  useEffect(() => {
    async function load() {
      try {
        const [allAvis, allJeux] = await Promise.all([
          getAvis(auth.role),
          jeu ? Promise.resolve(null) : getJeux(),
        ])
        setAvis(allAvis)
        if (!jeu && allJeux) {
          setJeu(allJeux.find(j => j.id === jeuId) ?? null)
        }
      } catch (e) {
        setError(e.message)
      } finally {
        setLoading(false)
      }
    }
    load()
  }, [])

  async function handleWriteAvis(e) {
    e.preventDefault()
    setError('')
    setSending(true)
    try {
      await writeAvis({
        description: form.description,
        note: Number(form.note),
        jeuId,
        joueurId: auth.userId,
      })
      const updated = await getAvis(auth.role)
      setAvis(updated)
      setForm({ description: '', note: '' })
    } catch (err) {
      setError(err.message)
    } finally {
      setSending(false)
    }
  }

  async function handleDelete(avisId) {
    try {
      await deleteAvis(avisId)
      setAvis(prev => prev.filter(a => a.id !== avisId))
    } catch (err) {
      setError(err.message)
    }
  }

  if (loading) return <div className="spinner">Chargement…</div>

  return (
    <div className="container page">
      <button className="back-btn" onClick={() => navigate('/')}>← Retour</button>

      {jeu && (
        <div className="card detail-header">
          <div className="info">
            <h2>{jeu.nom}</h2>
            <div className="tags">
              {jeu.editeur   && <span className="tag">{jeu.editeur.nom}</span>}
              {jeu.genre     && <span className="tag genre">{jeu.genre.nom}</span>}
              {jeu.plateformes?.map(p => <span key={p.id} className="tag platform">{p.nom}</span>)}
            </div>
            <div style={{ display: 'flex', gap: 20, marginTop: 10, color: 'var(--text-muted)', fontSize: '.88rem' }}>
              {jeu.dateDeSortie && <span>📅 {jeu.dateDeSortie}</span>}
              {jeu.prix != null && <span className="price">💶 {jeu.prix} €</span>}
            </div>
            {jeu.description && <p className="desc">{jeu.description}</p>}
          </div>
        </div>
      )}

      <div style={{ marginTop: 32 }}>
        <p className="section-title">
          Avis ({jeuAvis.length})
        </p>

        {jeuAvis.length === 0 ? (
          <p className="empty">Aucun avis pour ce jeu.</p>
        ) : (
          <div className="avis-list">
            {jeuAvis.map(a => (
              <div key={a.id} className="avis-card">
                <div className="content">
                  <p className="desc">{a.description}</p>
                  <p className="author">par {a.joueur?.pseudo}</p>
                </div>
                <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-end', gap: 8 }}>
                  <span className="note">{a.note}/10</span>
                  {isModo && (
                    <button className="btn btn-danger" style={{ padding: '4px 10px', fontSize: '.8rem' }}
                      onClick={() => handleDelete(a.id)}>
                      Supprimer
                    </button>
                  )}
                </div>
              </div>
            ))}
          </div>
        )}

        {!isModo && (
          <div className="card" style={{ marginTop: 8 }}>
            <p className="section-title">Laisser un avis</p>
            <form onSubmit={handleWriteAvis}>
              <div className="form-group">
                <label>Commentaire</label>
                <textarea required value={form.description}
                  onChange={e => setForm(f => ({ ...f, description: e.target.value }))}
                  placeholder="Votre avis sur ce jeu…" />
              </div>
              <div className="form-group">
                <label>Note (1 – 10)</label>
                <input type="number" min={1} max={10} required value={form.note}
                  onChange={e => setForm(f => ({ ...f, note: e.target.value }))} />
              </div>
              {error && <p className="error">{error}</p>}
              <button type="submit" className="btn btn-primary" disabled={sending}>
                {sending ? 'Envoi…' : 'Publier'}
              </button>
            </form>
          </div>
        )}

        {isModo && error && <p className="error">{error}</p>}
      </div>
    </div>
  )
}
