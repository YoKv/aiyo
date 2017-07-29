const path = require('path');
const express = require('express');
const expressVue = require('express-vue');
const qiniu = require('qiniu');
const app = express();

app.engine('vue', expressVue);
app.set('view engine', 'vue');
app.set('views', path.join(__dirname, '/views'));
app.set('vue', {
    componentsDir: __dirname + '/views/components'
});

var scope = {
    vue: {
        head: {
            title: '小仓库'
        }
    }
};
app.get('/', function (req, res) {
    res.render('index', scope);
});

app.get('/login', function (req, res) {
    var code = req.query.code;

    if (typeof code === 'undefined') {
        res.render('login', scope);
    } else if (code === '123bnm') {
        var accessKey = 'O7CprkTb7gRyvNADEz2-dO-5cMLCQc_SzydFoquK';
        var secretKey = 'ElYDT-iB8pdbUADZM0f7YENLGgkcDMKSh5PSTGpr';
        var mac = new qiniu.auth.digest.Mac(accessKey, secretKey);
        var options = {
            scope:  "file-data",
        };
        var putPolicy = new qiniu.rs.PutPolicy(options);
        var uploadToken = putPolicy.uploadToken(mac);
        var data = {
            data: {
           /*     bucketKey: 'file-data',*/
                uploadToken: uploadToken
            }
        };

        res.render('file', data);
    } else {
        res.render('index', scope);
    }

});

app.listen(3333);
console.log('Express server listening on port 3333');