require.ensure([], function (require) {
    var content = require('./a');
    console.log(content);
});

require.ensure([], function (require) {
    var content = require('./b');
    console.log(content);
});

//###################

// require('./a');
// require.ensure([], function (require) {
//     var content = require('./b');
//     console.log(content);
// });

//###################

// require.ensure(['./a.js'], function (require) {
//     var content = require('./b.js');
//     console.log(content);
// });