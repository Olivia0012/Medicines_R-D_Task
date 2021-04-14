const { createProxyMiddleware } = require('http-proxy-middleware');
const proxy = require('http-proxy-middleware');
module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target:  'https://oh-task-backend.herokuapp.com/',
      changeOrigin: true,
      pathRewrite: {
        '^/api': ''
      }
    })
  );
};
