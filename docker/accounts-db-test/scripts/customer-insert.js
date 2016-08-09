function get_results(result) {
    print(tojson(result));
}

function insert_customer(object) {
    print(db.customer.insert(object));
}

insert_customer({
    "_id": ObjectId("57a98d98e4b00679b4a830af"),
    "_class": "works.weave.socks.accounts.entities.Customer",
    "firstName": "Eve",
    "lastName": "Berger",
    "username": "Eve_Berger",
    "addresses": [DBRef("address", ObjectId("57a98d98e4b00679b4a830ad"))],
    "cards": [DBRef("card", ObjectId("57a98d98e4b00679b4a830ae"))]
});
insert_customer({
    "_id": ObjectId("57a98d98e4b00679b4a830b2"),
    "_class": "works.weave.socks.accounts.entities.Customer",
    "firstName": "User",
    "lastName": "Name",
    "username": "user",
    "addresses": [DBRef("address", ObjectId("57a98d98e4b00679b4a830b0"))],
    "cards": [DBRef("card", ObjectId("57a98d98e4b00679b4a830b1"))]
});
insert_customer({
    "_id": ObjectId("57a98d98e4b00679b4a830b5"),
    "_class": "works.weave.socks.accounts.entities.Customer",
    "firstName": "User1",
    "lastName": "Name1",
    "username": "user1",
    "addresses": [DBRef("address", ObjectId("57a98d98e4b00679b4a830b3"))],
    "cards": [DBRef("card", ObjectId("57a98d98e4b00679b4a830b4"))]
});

print("________ADDRESS DATA_______");
db.customer.find().forEach(get_results);
print("______END ADDRESS DATA_____");
