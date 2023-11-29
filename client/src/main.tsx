import React from 'react'
import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'
import './index.css'
import { FullQuestionProvider } from './contexts/FullQuestionContext.tsx'
import { UserProvider } from './contexts/UserContext.tsx'
import { router } from './router.tsx'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <UserProvider>
      <FullQuestionProvider>
          <RouterProvider router={router} />
      </FullQuestionProvider>
    </UserProvider>
  </React.StrictMode>,
)
