function get_results(result) {
    print(tojson(result));
}

function insert_card(object) {
    print(db.card.insert(object));
}

insert_card({
    "_id": ObjectId("57a98d98e4b00679b4a830ae"),
    "_class": "works.weave.socks.accounts.entities.Card",
    "longNum": "5953580604169678",
    "expires": "08/19",
    "ccv": "678"
});
insert_card({
    "_id": ObjectId("57a98d98e4b00679b4a830b1"),
    "_class": "works.weave.socks.accounts.entities.Card",
    "longNum": "5544154011345918",
    "expires": "08/19",
    "ccv": "958"
});
insert_card({
    "_id": ObjectId("57a98d98e4b00679b4a830b4"),
    "_class": "works.weave.socks.accounts.entities.Card",
    "longNum": "0908415193175205",
    "expires": "08/19",
    "ccv": "280"
});
insert_card({
    "_id": ObjectId("57a98ddce4b00679b4a830d2"),
    "_class": "works.weave.socks.accounts.entities.Card",
    "longNum": "5429804235432",
    "expires": "04/16",
    "ccv": "432"
});

print("________ADDRESS DATA_______");
db.card.find().forEach(get_results);
print("______END ADDRESS DATA_____");


