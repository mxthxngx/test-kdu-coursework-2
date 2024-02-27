import { it, expect, describe } from 'vitest';
import { fireEvent, screen } from '@testing-library/react';
import { TodoAdder } from '../components/todoadder/TodoAdder';
import { renderWithProviders } from './utils.test';
import '@testing-library/jest-dom';

describe('TodoAdder component', () => {
  it('renders the todo adder correctly', () => {
  renderWithProviders(<TodoAdder />);

    const headerElement = screen.getByText('Add Items');
    expect(headerElement).toBeInTheDocument();

    const inputElement = screen.getByPlaceholderText('Enter todo item');
    expect(inputElement).toBeInTheDocument();

    const submitButton = screen.getByRole('button', { name: 'Submit' });
    expect(submitButton).toBeInTheDocument();
  });

  it('adds todo item when submit button is clicked', () => {
    renderWithProviders(<TodoAdder />);

    const inputElement = screen.getByPlaceholderText('Enter todo item');
    const submitButton = screen.getByRole('button', { name: 'Submit' });

    fireEvent.change(inputElement, { target: { value: 'New todo item' } });
    fireEvent.click(submitButton);

    expect(inputElement).toHaveValue('');
  });

  it('updates input value when typing into the input field', () => {
    renderWithProviders(<TodoAdder />);

    const inputElement = screen.getByPlaceholderText('Enter todo item');

    fireEvent.change(inputElement, { target: { value: 'Test input' } });

    expect(inputElement).toHaveValue('Test input');
  });
});
