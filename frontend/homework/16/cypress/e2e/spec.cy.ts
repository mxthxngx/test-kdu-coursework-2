describe('TodoList Integration Testing', () => {
  it('loads website', () => {
    cy.visit('http://localhost:5173/');


    cy.get('[data-testid="cy-title"').should('exist').should('have.text','Item Lister');

  });
});
describe('TodoAdder component', () => {
  beforeEach(() => {
    cy.visit('http://localhost:5173/');
  });

  it('renders the todo adder correctly', () => {
    cy.get('.main-container').should('exist');
    cy.get('h2').should('contain.text', 'Add Items');
    cy.get('input[type="text"]').should('exist');
    cy.get('button.submit-btn').should('exist');
  });

  it('adds todo item when submit button is clicked', () => {
    const todoText = 'New todo item';
    cy.get('input[type="text"]').type(todoText);
    cy.get('button.submit-btn').click();
    cy.wait(200);
    cy.get('[data-testid="cy-input-todo"]').should('exist')
    cy.get('[data-testid="cy-input-todo"]').should('have.value', todoText);
  });

  it('updates input value when typing into the input field', () => {
    const typedText = 'Test input';
    cy.get('input[type="text"]').type(typedText);
    cy.get('input[type="text"]').should('have.value', typedText);
  });

  it('checks the search item',()=>{
    const todoText = 'carry toolkit';
    cy.get('input[type="text"]').type(todoText);
    cy.get('button.submit-btn').click();
    cy.wait(200);
    const todoText2 = 'remove trash';
    cy.get('input[type="text"]').first().type(todoText2);
    cy.get('button.submit-btn').click();
    cy.wait(200);

    cy.get('[data-testid="cy-search"]').should('exist').type(todoText2);
    cy.wait(1200);

    cy.get('[data-testid="cy-input-todo"]').should('have.value', todoText2);
    cy.get('[data-testid="cy-input-todo"]').should('not.have.value', todoText);

  })

});
