INSERT INTO LOCATION select  nextval('seq_location'), 'CITY' AS TYPELOCATION , ci.name || ' ' || ci.provinceabbreviation || ', '  || co.nameEnglish as nameEnglish,
ci.name || ' ' || ci.provinceabbreviation || ', '  || co.namePortuguese as namePortuguese,
ci.name || ' ' || ci.provinceabbreviation || ', '  || co.nameSpanish as nameSpanish,
ci.id  FROM CITY ci
INNER JOIN COUNTRY co ON co.iso = ci.countryiso;


INSERT INTO LOCATION select  nextval('seq_location'), 'PROVINCE' AS TYPELOCATION , p.name || '-' || co.iso as nameEnglish, p.name || '-' || co.iso as namePortuguese, p.name || '-' || co.iso as nameSpanish, p.id  from province p
INNER JOIN COUNTRY co ON co.externalId = p.countryid;



INSERT INTO LOCATION select  nextval('seq_location'), 'COUNTRY' AS TYPELOCATION , nameEnglish || ' (' || un || ')' as nameEnglish, 
namePortuguese || ' (' || un || ')' as namePortuguese,
nameSpanish || ' (' || un || ')' as nameSpanish, id from country;
