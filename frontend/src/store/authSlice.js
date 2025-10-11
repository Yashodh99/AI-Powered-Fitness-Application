
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  user: null,
  token: null,
  userId: null,
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    
    setCredentials: (state, action) => {
      const { token, user } = action.payload || {};
      state.token = token || null;
      state.user = user || null;
      state.userId = user?.sub || null;

      // persist (optional)
      if (token) localStorage.setItem('token', token);
      if (user)  localStorage.setItem('user', JSON.stringify(user));
      if (user?.sub) localStorage.setItem('userId', user.sub);
    },

   
    logout: (state) => {
      state.user = null;
      state.token = null;
      state.userId = null;
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      localStorage.removeItem('userId');
    },
  },
});


export const { setCredentials, logout } = authSlice.actions;
export default authSlice.reducer;
