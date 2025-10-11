import axios from "axios";

const API_URL = 'http://localhost:8080/api';


const api = axios.create({
    baseURL:API_URL
});


api.interceptors.request.use((config) =>{
    const userID = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if(token){
        config.headers['Authorization'] = `Bearere ${token}`;
    }
    if(userId){
        config.headers['X=User-ID'] = userId;
    }
    return config;

}
);


export const getActivities = () => api.get('/activities');
export const addActivity = (actvity) => api.post('/activity', activity);
export const getActivityDetail = (id) => api.get(`/recommendations/activity/${id}`);




