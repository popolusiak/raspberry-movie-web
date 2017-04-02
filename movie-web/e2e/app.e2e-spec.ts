import { MovieWebPage } from './app.po';

describe('movie-web App', () => {
  let page: MovieWebPage;

  beforeEach(() => {
    page = new MovieWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
