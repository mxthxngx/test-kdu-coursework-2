import { Alert, Snackbar } from '@mui/material';
import { useState } from 'react';

const ReusableSnackbar = ({ open, onClose, message, backgroundColor = 'red' }: any) => {
    const handleSnackbarClose = () => {
        onClose(false);
    };

    return (
        <Snackbar open={open} autoHideDuration={6000} onClose={handleSnackbarClose}>
        <Alert
          onClose={handleSnackbarClose}
          severity="error"
          variant="filled"
          sx={{ width: '100%' }}
        >
            {message}
            </Alert>
      </Snackbar>
    );
};

export default ReusableSnackbar;
