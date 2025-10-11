import { configureStore } from '@reduxjs/toolkit';
import authReducer from './authSlice.js'; 

console.log('authReducer type:', typeof authReducer); 

export const store = configureStore({
  reducer: {
    auth: authReducer,
  },
});
