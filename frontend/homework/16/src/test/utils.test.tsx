
import { render } from '@testing-library/react'
import {it,expect, describe} from  'vitest'
import { Provider, useDispatch  } from 'react-redux';
import type { RenderOptions } from '@testing-library/react'
import { RootState, Store } from '../components/redux/Store';
import { PropsWithChildren } from 'react';

interface ExtendedRenderOptions extends Omit<RenderOptions, 'queries'> {
  preloadedState?: Partial<RootState>,
  store?: typeof Store
}

export function renderWithProviders(
    ui: React.ReactElement,
    {
      preloadedState = {},
      store = Store,
      ...renderOptions
    }: ExtendedRenderOptions={}
)
{
    function Wrapper({children}:PropsWithChildren<{}>):JSX.Element{
        return <Provider store={store}>{children}</Provider>
    }
    return {store,...render(ui,{wrapper:Wrapper,...renderOptions})}
}


    it('checks if 5 is 5',()=>{
        const num = 5
        expect(num).toBe(5)
    })
