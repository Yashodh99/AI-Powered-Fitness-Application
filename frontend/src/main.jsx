import React from 'react'
import ReactDOM from 'react-dom/client'

import { Provider } from 'react-redux'
import { store } from './store/store'

import App from './App'

// As of React 18
const root = ReactDOM.createRoot(document.getElementById('root'))
root.render(
  <AuthProvider authConfig={authConfig}
  loadingComponent ={<div>loading...</div>}>

  <Provider store={store}>
    <App />
  </Provider>
 </AuthProvider>
)