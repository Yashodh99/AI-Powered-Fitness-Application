import { configureStore } from '@reduxjs/toolkit';
import authReducer from './authSlice.js'; // note the .js extension

console.log('authReducer type:', typeof authReducer); // should log "function"

export const store = configureStore({
  reducer: {
    auth: authReducer,
  },
});
