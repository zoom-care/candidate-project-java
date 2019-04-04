insert into node values ();
select @ceo := scope_identity();
insert into property (node_id, key, value) values (@ceo, 'title', 'CEO');

insert into node (parent_node_id) values (@ceo);
select @vpsales := scope_identity();
insert into property (node_id, key, value) values (@vpsales, 'title', 'Vice President of Sales');

insert into node(parent_node_id) values (@vpsales);
select @nasales := scope_identity();
insert into property (node_id, key, value) values (@nasales, 'title', 'Regional Director of Sales');
insert into property (node_id, key, value) values (@nasales, 'region', 'North America');

insert into node(parent_node_id) values (@nasales);
select @salesrep1 := scope_identity();
insert into property (node_id, key, value) values (@salesrep1, 'title', 'Sales Representative');

insert into node(parent_node_id) values (@vpsales);
select @eusales := scope_identity();
insert into property (node_id, key, value) values (@eusales, 'title', 'Regional Director of Sales');
insert into property (node_id, key, value) values (@eusales, 'region', 'Europe');

insert into node (parent_node_id) values (@ceo);
select @vpp := scope_identity();
insert into property (node_id, key, value) values (@vpp, 'title', 'Vice President of People');

insert into node (parent_node_id) values (@ceo);
select @vpm := scope_identity();
insert into property (node_id, key, value) values (@vpm, 'title', 'Vice President of Marketing');

insert into node(parent_node_id) values (@vpm);
select @namarketing := scope_identity();
insert into property (node_id, key, value) values (@namarketing, 'title', 'Regional Director of Marketing');
insert into property (node_id, key, value) values (@namarketing, 'region', 'North America');

insert into node(parent_node_id) values (@vpm);
select @eumarketing := scope_identity();
insert into property (node_id, key, value) values (@eumarketing, 'title', 'Regional Director of Marketing');
insert into property (node_id, key, value) values (@eumarketing, 'region', 'Europe');