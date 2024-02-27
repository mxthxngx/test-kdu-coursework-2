import React from 'react';
import {it,expect, describe} from  'vitest'
import { fireEvent, render ,screen} from '@testing-library/react';
import { Header } from '../components/header/Header'; 
import { renderWithProviders } from './utils.test'; 
import '@testing-library/jest-dom';
describe('Header component', () => {
  it('renders the header correctly', () => {
    const { getByText, getByPlaceholderText } = renderWithProviders(<Header />);
        const element = screen.getByText('Item Lister')
        expect(element).toBeInTheDocument();
    expect(getByPlaceholderText('Search items')).toBeInTheDocument();
  });

  it('calls setItemListHandler with correct input when search value changes', () => {
    const { getByPlaceholderText } = renderWithProviders(<Header />);
    const searchInput = getByPlaceholderText('Search items');

    fireEvent.change(searchInput, { target: { value: 'test input' } });

   
    expect(searchInput).toHaveValue('test input');
  });
});
