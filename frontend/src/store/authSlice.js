import { createSlice } from '@reduxjs/toolkit';

const initialState = { user: null, token: null, loading: false, error: null };

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    loginStart(state) { state.loading = true; state.error = null; },
    loginSuccess(state, action) {
      state.loading = false;
      state.user = action.payload.user;
      state.token = action.payload.token;
    },
    loginFailure(state, action) { state.loading = false; state.error = action.payload; },
    logout(state) { state.user = null; state.token = null; },
  },
});

// named exports (optional to use elsewhere)
export const { loginStart, loginSuccess, loginFailure, logout } = authSlice.actions;

// DEFAULT export = the reducer (this is what the store needs)
export default authSlice.reducer;
