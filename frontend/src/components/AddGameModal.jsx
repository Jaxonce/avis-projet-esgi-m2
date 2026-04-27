import { useState } from 'react'

export default function AddGameModal({ referentiel, onSubmit, onClose }) {
  const [form, setForm]   = useState({ plateformeIds: [] })
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  function set(field) {
    return (e) => setForm(f => ({ ...f, [field]: e.target.value }))
  }

  function togglePlateforme(id) {
    setForm(f => ({
      ...f,
      plateformeIds: f.plateformeIds.includes(id)
        ? f.plateformeIds.filter(p => p !== id)
        : [...f.plateformeIds, id],
    }))
  }

  async function handleSubmit(e) {
    e.preventDefault()
    if (form.plateformeIds.length === 0) { setError('Sélectionnez au moins une plateforme.'); return }
    setError('')
    setLoading(true)
    try {
      await onSubmit({
        nom: form.nom,
        dateDeSortie: form.dateDeSortie,
        description: form.description,
        prix: Number(form.prix),
        editeurId: Number(form.editeurId),
        genreId: Number(form.genreId),
        plateformeIds: form.plateformeIds,
      })
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="modal-overlay" onClick={e => e.target === e.currentTarget && onClose()}>
      <div className="modal">
        <div className="modal-header">
          <h3>Ajouter un jeu</h3>
          <button className="btn-close" onClick={onClose}>×</button>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Nom</label>
            <input required value={form.nom || ''} onChange={set('nom')} />
          </div>

          <div className="form-group">
            <label>Date de sortie</label>
            <input type="date" required value={form.dateDeSortie || ''} onChange={set('dateDeSortie')} />
          </div>

          <div className="form-group">
            <label>Prix (€)</label>
            <input type="number" step="0.01" min="0" required value={form.prix || ''} onChange={set('prix')} />
          </div>

          <div className="form-group">
            <label>Description</label>
            <textarea value={form.description || ''} onChange={set('description')} />
          </div>

          <div className="form-group">
            <label>Éditeur</label>
            <select required value={form.editeurId || ''} onChange={set('editeurId')}>
              <option value="">— Choisir —</option>
              {referentiel.editeurs.map(e => (
                <option key={e.id} value={e.id}>{e.nom}</option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Genre</label>
            <select required value={form.genreId || ''} onChange={set('genreId')}>
              <option value="">— Choisir —</option>
              {referentiel.genres.map(g => (
                <option key={g.id} value={g.id}>{g.nom}</option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Plateformes</label>
            <div className="checkbox-group">
              {referentiel.plateformes.map(p => (
                <label key={p.id}>
                  <input type="checkbox"
                    checked={form.plateformeIds.includes(p.id)}
                    onChange={() => togglePlateforme(p.id)} />
                  {p.nom}
                </label>
              ))}
            </div>
          </div>

          {error && <p className="error">{error}</p>}

          <div style={{ display: 'flex', gap: 12, marginTop: 8 }}>
            <button type="submit" className="btn btn-primary" disabled={loading}>
              {loading ? 'Ajout…' : 'Ajouter'}
            </button>
            <button type="button" className="btn btn-outline" onClick={onClose}>Annuler</button>
          </div>
        </form>
      </div>
    </div>
  )
}
