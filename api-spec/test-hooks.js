const hooks = require('hooks');
const {MongoClient} = require('mongodb');
const ObjectID = require('mongodb').ObjectID;

let db;

const address = [
    {"_id":ObjectID("579f21ae98684924944651bd"),"_class":"works.weave.socks.accounts.entities.Address","number":"69","street":"Wilson Street","city":"Hartlepool","postcode":"TS26 8JU","country":"United Kingdom"},
    {"_id":ObjectID("579f21ae98684924944651c0"),"_class":"works.weave.socks.accounts.entities.Address","number":"3","street":"Radstone Way","city":"Northampton","postcode":"NN2 8NT","country":"United Kingdom"},
    {"_id":ObjectID("579f21ae98684924944651c3"),"_class":"works.weave.socks.accounts.entities.Address","number":"3","street":"Radstone Way","city":"Northampton","postcode":"NN2 8NT","country":"United Kingdom"}
];


const card = [
    {"_id":ObjectID("579f21ae98684924944651be"),"_class":"works.weave.socks.accounts.entities.Card","longNum":"8575776807334952","expires":"08/19","ccv":"014"},
    {"_id":ObjectID("579f21ae98684924944651c1"),"_class":"works.weave.socks.accounts.entities.Card","longNum":"8918468841895184","expires":"08/19","ccv":"597"},
    {"_id":ObjectID("579f21ae98684924944651c4"),"_class":"works.weave.socks.accounts.entities.Card","longNum":"6426429851404909","expires":"08/19","ccv":"381"}
];

const customer = [
    {"_id":"579f21ae98684924944651bf","_class":"works.weave.socks.accounts.entities.Customer","firstName":"Eve","lastName":"Berger","username":"Eve_Berger","addresses":[{"$ref":"address","$id":ObjectID("579f21ae98684924944651bd")}],"cards":[{"$ref":"card","$id":ObjectID("579f21ae98684924944651be")}]
    },
    {"_id":"579f21ae98684924944651c2","_class":"works.weave.socks.accounts.entities.Customer","firstName":"User","lastName":"Name","username":"user","addresses":[{"$ref":"address","$id":ObjectID("579f21ae98684924944651c0")}],"cards":[{"$ref":"card","$id":ObjectID("579f21ae98684924944651c1")}]},
    {"_id":"579f21ae98684924944651c5","_class":"works.weave.socks.accounts.entities.Customer","firstName":"User1","lastName":"Name1","username":"user1","addresses":[{"$ref":"address","$id":ObjectID("579f21ae98684924944651c3")}],"cards":[{"$ref":"card","$id":ObjectID("579f21ae98684924944651c4")}]}
];


// Setup database connection before Dredd starts testing
hooks.beforeAll((transactions, done) => {
    var MongoEndpoint = process.env.MONGO_ENDPOINT ||  'mongodb://localhost:32769/data';
    // console.info('MongoEndpoint: ' + MongoEndpoint);
    MongoClient.connect(MongoEndpoint, function(err, conn) {
	db = conn;
	db.dropDatabase();
	done(err);
    });

});

// Close database connection after Dredd finishes testing
hooks.afterAll((transactions, done) => {
    db.close();
    done();
});



// After each test clear contents of the database (we want isolated tests)
hooks.afterEach((transaction, done) => {
    db.dropDatabase();
    done();
    
});

hooks.beforeEach((transaction, done) => {
    var promisesToKeep = [
	db.collection('customer').insertMany(customer),
	db.collection('card').insertMany(card),
	db.collection('address').insertMany(address),
    ];
    Promise.all(promisesToKeep).then(() => { done(); });
});
