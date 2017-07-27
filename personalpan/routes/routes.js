//express路由

module.exports = function (app) {
  app.get('/', function (req, res) {
     var scope = {
        data: {
            title: pageTitle,
            message: 'Hello!',
            users: users
        },
        vue: {
            head: {
                title: pageTitle,
                meta: [
                    { property:'og:title', content: pageTitle},
                    { name:'twitter:title', content: pageTitle}
                ],
                structuredData: {
                    "@context": "http://schema.org",
                    "@type": "Organization",
                    "url": "http://www.your-company-site.com",
                    "contactPoint": [{
                        "@type": "ContactPoint",
                        "telephone": "+1-401-555-1212",
                        "contactType": "customer service"
                    }]
                }
            },
            components: ['users', 'messageComp'],
            mixins: [exampleMixin]
        }
    };
    res.render('index', scope);
  });
  app.get('/customer', function(req, res){
   var user = users.filter(function(item) {
        return item.name === req.params.userName;
    })[0];
    res.render('user', {
        data: {
            title: 'Hello My Name is',
            user: user
        }
    });
  });
  app.get('/admin', function(req, res){
    res.send('admin page');
  });
};