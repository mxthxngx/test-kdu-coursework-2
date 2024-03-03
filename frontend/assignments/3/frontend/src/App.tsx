import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css'
import { Header } from './components/header/Header';
import { MainPortfolio } from './components/portfolio/MainPortfolio';
import { Summarizer } from './components/summarizer/Summarizer';
import { Dashboard } from './components/dashboard/Dashboard';
import { ThemeProvider } from '@mui/material';
import { theme } from './components/utils/themes';
import { AppDispatch } from './components/redux/store/store';
import { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { fetchDashboardData } from './components/redux/thunk/dashboardThunk';
import { StockPage } from './components/stockpage/StockPage';
import { Socket, io } from 'socket.io-client';
import { setUser } from './components/redux/slice/userSlice';

function App() {
  const dispatch:AppDispatch = useDispatch();
  const [socket, setSocket] = useState<Socket<any, any> | null>(null);
  
  useEffect(() => {
    const newSocket = io(`http://${window.location.hostname}:3000`);
    setSocket(newSocket);
    newSocket.on('username', (receivedUsername: string) => {
      dispatch(setUser(receivedUsername));
      console.log("username assigned is: ",receivedUsername)
    });
    return () => newSocket.close();
    
  }, [setSocket]);

  useEffect(() => {
    dispatch(fetchDashboardData());
  }, [dispatch]);

  return (
    <ThemeProvider theme={theme}>

        <BrowserRouter>
          <div className="App" style={{ fontFamily: 'Poppins, sans-serif', fontSize: '1.1rem' }}>
            <Header />
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/portfolio" element={<MainPortfolio />} />
              <Route path="/summarizer" element={<Summarizer />} />
              <Route path="/stock/:symbol" element={<StockPage socket = {socket}/>}/>
            </Routes>
          </div>
        </BrowserRouter>

    </ThemeProvider>
  );
}

export default App;
