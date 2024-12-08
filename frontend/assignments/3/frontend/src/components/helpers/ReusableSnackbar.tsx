import { Alert, Snackbar } from '@mui/material';
import { useState } from 'react';

/**
 * Renders a reusable snackbar component with customizable message, background color, and close behavior.
 *
 * @param {any} open - Flag to determine if the snackbar is open
 * @param {function} onClose - Callback function to handle snackbar close
 * @param {string} message - The message to be displayed in the snackbar
 * @param {string} backgroundColor - The background color of the snackbar
 * @return {JSX.Element} The rendered snackbar component
 */
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
