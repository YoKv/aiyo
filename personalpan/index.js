const path = require('path');
const express = require('express');
const expressVue = require('express-vue');
const app = express();

app.engine('vue', expressVue);
app.set('view engine', 'vue');
app.set('views', path.join(__dirname, '/views'));
//Optional if you want to specify the components directory separate to your views, and/or specify a custom layout. 
app.set('vue', {
    //ComponentsDir is optional if you are storing your components in a different directory than your views 
    componentsDir: __dirname + '/components',
    //Default layout is optional it's a file and relative to the views path, it does not require a .vue extension. 
    //If you want a custom layout set this to the location of your layout.vue file. 
    defaultLayout: 'layout'
});

const routes = require(path.join(__dirname, '/routes/routes'));

app.listen(3000);