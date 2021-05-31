import React from 'react';
import $ from 'jquery';

window.jQuery = $;
window.$ = $;
global.jQuery = $;

const Dashboard = React.lazy(() => import('./views/Dashboard'));

const Settings = React.lazy(() => import('./views/Settings'));

const Historic = React.lazy(() => import('./views/Historic'));

//const Login = React.lazy(() => import('./views/Login'));



const UIBasicButton = React.lazy(() => import('./components/UIElements/Button'));
const UIBasicBadges = React.lazy(() => import('./components/UIElements/Badges'));
const UIBasicBreadcrumbPagination = React.lazy(() => import('./components/UIElements/BreadcrumbPagination'));

const UIBasicCollapse = React.lazy(() => import('./components/UIElements/Collapse'));
const UIBasicTabsPills = React.lazy(() => import('./components/UIElements/TabsPills'));
const UIBasicBasicTypography = React.lazy(() => import('./components/UIElements/Typography'));

const FormsElements = React.lazy(() => import('./components/Forms/FormsElements'));

const BootstrapTable = React.lazy(() => import('./components/Tables/BootstrapTable'));

const Nvd3Chart = React.lazy(() => import('./components/Charts/Nvd3Chart'));

const GoogleMap = React.lazy(() => import('./components/Maps/GoogleMap/index'));

const OtherSamplePage = React.lazy(() => import('./components/Other/SamplePage'));
const OtherDocs = React.lazy(() => import('./components/Other/Docs'));

const routes = [
    { path: '/dashboard', exact: true, name: 'Default', component: Dashboard },
    { path: '/settings', exact: true, name: 'Default', component: Settings },
    { path: '/historic', exact: true, name: 'Default', component: Historic },
    { path: '/basic/button', exact: true, name: 'Basic Button', component: UIBasicButton },
    { path: '/basic/badges', exact: true, name: 'Basic Badges', component: UIBasicBadges },
    { path: '/basic/breadcrumb-paging', exact: true, name: 'Basic Breadcrumb Pagination', component: UIBasicBreadcrumbPagination },
    { path: '/basic/collapse', exact: true, name: 'Basic Collapse', component: UIBasicCollapse },
    { path: '/basic/tabs-pills', exact: true, name: 'Basic Tabs & Pills', component: UIBasicTabsPills },
    { path: '/basic/typography', exact: true, name: 'Basic Typography', component: UIBasicBasicTypography },
    { path: '/forms/form-basic', exact: true, name: 'Forms Elements', component: FormsElements },
    { path: '/tables/bootstrap', exact: true, name: 'Bootstrap Table', component: BootstrapTable },
    { path: '/charts/nvd3', exact: true, name: 'Nvd3 Chart', component: Nvd3Chart },
    { path: '/maps/google-map', exact: true, name: 'Google Map', component: GoogleMap },
    { path: '/sample-page', exact: true, name: 'Sample Page', component: OtherSamplePage },
    { path: '/docs', exact: true, name: 'Documentation', component: OtherDocs },
];

export default routes;