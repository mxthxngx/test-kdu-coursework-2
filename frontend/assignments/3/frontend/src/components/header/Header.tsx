import { useState } from 'react';
import { Link } from 'react-router-dom';
import {
  AppBar,
  Toolbar,
  IconButton,
  Typography,
  Menu,
  MenuItem,
  useMediaQuery,
  Box,
} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import Button from '@mui/material/Button';
import stockRising from '../../assets/money-graph-with-up-arrow-svgrepo-com.svg';

export function Header() {
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);

  const handleMenu = (event: any) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const isMobile = useMediaQuery('(max-width:600px)');

  return (
    <div className="header" >
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="sticky" elevation={0}  >
          <Toolbar>
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
              <Link to="/" style={{ textDecoration: 'none', color: 'inherit' }}>
                <img src={stockRising} alt="logo" style={{ height: '30px', paddingRight: '20px' }} />
              </Link>
              KDU StockMarket App
            </Typography>
            {isMobile && (
              <IconButton
                onClick={handleMenu}
                size="large"
                edge="start"
                color="inherit"
                aria-label="menu"
                sx={{ mr: 2 }}
              >
                <MenuIcon />
              </IconButton>
            )}
            {!isMobile && (
              <div>
                <Button color="inherit">
                  <Link to="/summarizer" style={{ textDecoration: 'none', color: 'inherit' }}>
                    Summarizer
                  </Link>
                </Button>
                <Button color="inherit">
                  <Link to="/portfolio" style={{ textDecoration: 'none', color: 'inherit' }}>
                    My Portfolio
                  </Link>
                </Button>
              </div>
            )}
          </Toolbar>
        </AppBar>
      </Box>
      <Menu
        id="menu-appbar"
        anchorEl={anchorEl}
        anchorOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        keepMounted
        transformOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        open={open}
        onClose={handleClose}
      >
        <MenuItem onClick={handleClose}>
          <Link to="/summarizer" style={{ textDecoration: 'none', color: 'inherit' }}>
            Summarizer
          </Link>
        </MenuItem>
        <MenuItem onClick={handleClose}>
          <Link to="/portfolio" style={{ textDecoration: 'none', color: 'inherit' }}>
            My Portfolio
          </Link>
        </MenuItem>
      </Menu>
    </div>
  );
}
