import { createTheme } from "@mui/material";

export const theme  = createTheme({
    palette:{
        primary:{
            main: '#1871c2',
        }
    },
    typography: {
        fontFamily: 'Poppins, sans-serif',
        button:{
            textTransform:'none',
            fontSize:'1rem',
        }
    }
})