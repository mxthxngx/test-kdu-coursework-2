import { render, fireEvent, screen } from '@testing-library/react';
import { Items } from '../components/items/Items';
import { Provider } from 'react-redux';
import { RootState, Store } from '../components/redux/Store';
import {it,expect, describe} from  'vitest'
import { setItemList } from '../components/redux/ItemSlice';

describe('Items component', () => {
  it('renders items correctly', () => {
    const mockItem = {
      id: 1,
      todo: 'Sample Todo',
      isStriked: false,
      isDone:false
    };
    Store.dispatch(setItemList([mockItem]))
    const {container} = render(
      <Provider store={Store}>
        <Items />
      </Provider>
    );
    const itemElement = container.getElementsByClassName("input-div-text")[0]
    expect(itemElement).toBeInTheDocument();
  });

  it('toggles item strike-through correctly', () => {
    const mockItem = {
      id: 1,
      todo: 'Sample Todo',
      isStriked: false,
      isDone:false
    };
    Store.dispatch(setItemList([mockItem]))
    const {container}=render(
      <Provider store={Store}>
        <Items />
      </Provider>
    );
    
    const itemElement =container.getElementsByClassName("todo-item")[0];
    fireEvent.click(itemElement);
    expect(itemElement).toHaveStyle('text-decoration: line-through');
  });

  it('handles item deletion correctly', () => {
    const mockItem = {
      id: 1,
      todo: 'Sample Todo',
      isStriked: false,
    };

    render(
      <Provider store={Store}>
        <Items />
      </Provider>
    );

    const deleteButton = screen.getByText('x');
    fireEvent.click(deleteButton);

    expect(screen.queryByText(mockItem.todo)).toBeNull();
  });
});
