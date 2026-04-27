import { Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider, useAuth } from './AuthContext'
import Header from './components/Header'
import AuthPage from './pages/AuthPage'
import JeuxPage from './pages/JeuxPage'
import JeuDetailPage from './pages/JeuDetailPage'

function AppRoutes() {
  const { auth } = useAuth()

  if (!auth) {
    return (
      <Routes>
        <Route path="*" element={<AuthPage />} />
      </Routes>
    )
  }

  return (
    <>
      <Header />
      <Routes>
        <Route path="/"     element={<JeuxPage />} />
        <Route path="/jeu/:id" element={<JeuDetailPage />} />
        <Route path="*"    element={<Navigate to="/" />} />
      </Routes>
    </>
  )
}

export default function App() {
  return (
    <AuthProvider>
      <AppRoutes />
    </AuthProvider>
  )
}
