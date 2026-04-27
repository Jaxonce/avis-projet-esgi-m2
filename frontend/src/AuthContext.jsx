import { createContext, useContext, useState } from 'react'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [auth, setAuth] = useState(() => {
    const token = localStorage.getItem('token')
    const role  = localStorage.getItem('role')
    const pseudo = localStorage.getItem('pseudo')
    const userId = localStorage.getItem('userId')
    return token ? { token, role, pseudo, userId: userId ? Number(userId) : null } : null
  })

  function signIn({ token, role, pseudo, userId }) {
    localStorage.setItem('token',  token)
    localStorage.setItem('role',   role)
    localStorage.setItem('pseudo', pseudo)
    if (userId != null) localStorage.setItem('userId', String(userId))
    setAuth({ token, role, pseudo, userId })
  }

  function signOut() {
    localStorage.clear()
    setAuth(null)
  }

  return (
    <AuthContext.Provider value={{ auth, signIn, signOut }}>
      {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => useContext(AuthContext)
