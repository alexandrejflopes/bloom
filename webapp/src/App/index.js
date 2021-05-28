import React, { Component, Suspense } from 'react';
import Loadable from 'react-loadable';
import { Route, Switch } from 'react-router-dom';
//import '../../node_modules/font-awesome/scss/font-awesome.scss';
import '../../node_modules/font-awesome/css/font-awesome.css';
import Aux from "../hoc/_Aux";
import routes from "../route";
import Loader from './layout/Loader';
import ScrollToTop from './layout/ScrollToTop';




const AdminLayout = Loadable({
    loader: () => import('./layout/AdminLayout'),
    loading: Loader
});

class App extends Component {
    render() {
        const menu = routes.map((route, index) => {
          return (route.component) ? (
              <Route
                  key={index}
                  path={route.path}
                  exact={route.exact}
                  name={route.name}
                  render={props => (
                      <route.component {...props} />
                  )} />
          ) : (null);
        });

        return (
            <Aux>
                <ScrollToTop>
                    <Suspense fallback={<Loader/>}>
                        <Switch>
                            {menu}
                            <Route path="/" component={AdminLayout} />
                        </Switch>
                    </Suspense>
                </ScrollToTop>
            </Aux>
        );
    }
}

export default App;
