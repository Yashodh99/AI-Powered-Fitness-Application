import { createSlice } from '@reduxjs/toolkit';

const initialState = { user: null, token: null, loading: false, error: null };

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setCredentiasl:(state,action)=>{
      

    },
    
    logout(state) { state.user = null; state.token = null; },
  },
});


export const { loginStart, loginSuccess, loginFailure, logout } = authSlice.actions;


export default authSlice.reducer;
