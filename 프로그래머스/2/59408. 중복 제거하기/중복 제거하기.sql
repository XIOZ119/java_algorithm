SELECT COUNT(DISTINCT a.name)
FROM animal_ins a
WHERE a.name IS NOT NULL;
