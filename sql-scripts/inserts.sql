INSERT INTO LOCATIONS select  nextval('seq_locations'), 'CITY' AS TYPELOCATION , ci.name || ' ' || ci.provinceabbreviation || ', '  || co.nameEnglish as nameEnglish,
ci.name || ' ' || ci.provinceabbreviation || ', '  || co.namePortuguese as namePortuguese,
ci.name || ' ' || ci.provinceabbreviation || ', '  || co.nameSpanish as nameSpanish,
ci.id,
ci.alternativeNames 
FROM CITY ci
INNER JOIN COUNTRY co ON co.iso = ci.countryiso;


INSERT INTO LOCATIONS select  nextval('seq_locations'), 'PROVINCE' AS TYPELOCATION , 
p.name || '-' || co.iso as nameEnglish, p.name || '-' || co.iso as namePortuguese, 
p.name || '-' || co.iso as nameSpanish, 
p.id  from province p
INNER JOIN COUNTRY co ON co.id = p.countryid;


INSERT INTO LOCATIONS select  nextval('seq_locations'), 'COUNTRY' AS TYPELOCATION , nameEnglish || ' (' || un || ')' as nameEnglish, 
namePortuguese || ' (' || un || ')' as namePortuguese,
nameSpanish || ' (' || un || ')' as nameSpanish, id, alternativeNames from country;



insert into OrderAvatar select nextval('seq_order_avatar'), id, productImageBase64 from Orders;

