import {BrowserRouter as Router , Navigate,Route, Routes, useLocation} from "react-router";
import Button from '@mui/material/Button';
import { useContext } from "react";
import { useEffect } from "react";
import { useState } from "react";

const ActivitiesPage = () => {
  return(
  <Box sx={{p:2, border:'1pz dashed grey'}}>
  <ActivityForm onActivitiesAdded = {()=> window.location.reload()} />
  <ActivityList />
  </Box>);
}

function App() {


  const{token, toeknData, logIn, logOut, isAuthenticated} = useContext(AuthContext);
  const dispatch = useDispatch();

  const[authReady, setAuthReady] = useState(false);
  useEffect(()=> {
    if(token){
      dispatch(setCredentials({token,user:toeknData}));
      setAuthReady(true);

    }
  },[token,toeknData,dispatch]);
  return (
    <Router>
      {!token ? (
       <Button variant = "cantained" color ="#dc004e"
       onClick={() =>{
        logIn();
       }}> LOGIN </Button>
      ):(
        // <div>
        //   <pre>{JSON.stringify(toeknData,null,2)}</pre>
        //   <pre>{JSON.stringify(token,null,2)}</pre>
        // </div>

         <Box component="section" sx={{ p: 2, border: '1px dashed grey' }}>
      <Routes>
        <Route path="/activities" element={<ActivitiesPage/>}/>
        <Route path="/activities/:id" element={<ActivityDetail/>}/>

        <Route path ="/" element={token ? <Navigate to="/activities" replace/> : <div>Welcome Please Login.</div>} />
      </Routes>
    </Box>

      )}
    </Router>
    
  )
}

export default App
