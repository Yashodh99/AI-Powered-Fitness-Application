import React, { useEffect, useState } from 'react'
import { CardContent, Grid2, Typography } from '@mui/material'

const ActivityList =() => {

    const[activities, setActivities] = useState([]);
    const navigate = useNavigate();

    const fetchActivities = async() => {
        try{
            const response = await getActivities();
            setActivities(response.data);

        }catch(error){
            console.error(error);

        }
    };

    useEffect(()=>{
        fetchActivities();
    },[]);
    return(

        <Grid2 container spacing={2}>
            {activities.map((activity)=>(
                <Grid2 container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
                    <Card sx = {{cursor: 'pointer'}}
                    onClick={()=> navigate(`/activities/${activity.id}`)}>
                        <CardContent>
                            <Typography variant='h6'>activity.type</Typography>
                            <Typography>Duration: {activity.duration}</Typography>
                            <Typography>Calories: {activity.caloriesBurned}</Typography>
                        </CardContent>

                    </Card>
                    </Grid2>
            ))}

    
</Grid2>
    )
}

export default ActivityList