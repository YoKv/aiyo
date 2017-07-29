//express路由,暂时没用到，重构时再整理代码结构

module.exports = function (app) {
    //主页
    app.get('/', function(req, res){
        res.render('index', {
            data: {
                title: 'yokv的小仓库'
            }
        });
    });

};