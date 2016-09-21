-- Base revision, fake it's from the official launch of the project on March 31st, 2016.

INSERT INTO revinfo(rev, revtstmp) VALUES (1, 1459382400);

-- category
INSERT INTO category_aud(`rev`, `revtype`, `id`, `title_en`, `title_fr`, `description_en`, `description_fr`, `thumbnail`, `enabled`)
  SELECT 1, 0, `id`, `title_en`, `title_fr`, `description_en`, `description_fr`, `thumbnail`, `enabled`
  FROM category;

-- subcategory
INSERT INTO subcategory_aud(`rev`, `revtype`, `id`, `category_id`, `title_en`, `title_fr`, `description_en`, `description_fr`, `enabled`)
  SELECT 1, 0, `id`, `category_id`, `title_en`, `title_fr`, `description_en`, `description_fr`, `enabled`
  FROM subcategory;

-- collection
INSERT INTO collection_aud(`rev`, `revtype`, `id`, `description_en`, `description_fr`, `title_en`, `title_fr`, `subcategory_id`, `enabled`)
  SELECT 1, 0, `id`, `description_en`, `description_fr`, `title_en`, `title_fr`, `subcategory_id`, `enabled`
  FROM collection;

-- seed
INSERT INTO seed_aud(`rev`, `revtype`, `id`, `collection_id`, `url`)
  SELECT 1, 0, `id`, `collection_id`, `url`
  FROM seed;
