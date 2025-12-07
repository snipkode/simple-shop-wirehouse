-- Sample data for Items
INSERT INTO items (id, name, description, category, active) VALUES
(1, 'Kaos Polos', 'Kaos katun nyaman untuk keseharian', 'Pakaian', true),
(2, 'Celana Jeans', 'Celana jeans denim klasik dengan potongan regular', 'Pakaian', true),
(3, 'Sepatu Lari', 'Sepatu lari ringan dengan sol empuk', 'Alas Kaki', true),
(4, 'Tas Ransel', 'Tas ransel tahan air dengan banyak kompartemen', 'Aksesoris', true),
(5, 'Jam Tangan', 'Jam tangan analog stylish dengan tali kulit', 'Aksesoris', true);

-- Sample Variants for Item 1 - Kaos Polos
INSERT INTO variants (id, item_id, name, price, active) VALUES
(1, 1, 'Putih - S', 109000, true),
(2, 1, 'Putih - M', 109000, true),
(3, 1, 'Putih - L', 109000, true),
(4, 1, 'Hitam - S', 129000, true),
(5, 1, 'Hitam - M', 129000, true),
(6, 1, 'Hitam - L', 129000, true),
(7, 1, 'Biru - M', 119000, true);

-- Sample Variants for Item 2 - Celana Jeans
INSERT INTO variants (id, item_id, name, price, active) VALUES
(8, 2, 'Biru - Ukuran 30', 359000, true),
(9, 2, 'Biru - Ukuran 32', 359000, true),
(10, 2, 'Biru - Ukuran 34', 359000, true),
(11, 2, 'Hitam - Ukuran 30', 399000, true),
(12, 2, 'Hitam - Ukuran 32', 399000, true),
(13, 2, 'Wash Gelap - Ukuran 32', 379000, true);

-- Sample Variants for Item 3 - Sepatu Lari
INSERT INTO variants (id, item_id, name, price, active) VALUES
(14, 3, 'Merah - Ukuran 8', 599000, true),
(15, 3, 'Merah - Ukuran 9', 599000, true),
(16, 3, 'Merah - Ukuran 10', 599000, true),
(17, 3, 'Hitam - Ukuran 8', 649000, true),
(18, 3, 'Hitam - Ukuran 9', 649000, true),
(19, 3, 'Hitam - Ukuran 10', 649000, true),
(20, 3, 'Putih - Ukuran 9', 629000, true);

-- Sample Variants for Item 4 - Tas Ransel
INSERT INTO variants (id, item_id, name, price, active) VALUES
(21, 4, 'Hitam - Standar', 299000, true),
(22, 4, 'Biru - Standar', 299000, true),
(23, 4, 'Abu - Edisi Travel', 459000, true),
(24, 4, 'Hijau - Edisi Outdoor', 419000, true);

-- Sample Variants for Item 5 - Jam Tangan
INSERT INTO variants (id, item_id, name, price, active) VALUES
(25, 5, 'Tali Kulit Hitam', 679000, true),
(26, 5, 'Tali Kulit Coklat', 679000, true),
(27, 5, 'Tali Logam Silver', 999000, true);

-- Stock information for each variant
INSERT INTO stocks (id, variant_id, quantity, reserved) VALUES
(1, 1, 50, false),   -- Putih - S Kaos Polos: 50
(2, 2, 45, false),   -- Putih - M Kaos Polos: 45
(3, 3, 30, false),   -- Putih - L Kaos Polos: 30
(4, 4, 40, false),   -- Hitam - S Kaos Polos: 40
(5, 5, 35, false),   -- Hitam - M Kaos Polos: 35
(6, 6, 25, false),   -- Hitam - L Kaos Polos: 25
(7, 7, 20, false),   -- Biru - M Kaos Polos: 20
(8, 8, 15, false),   -- Biru Jeans Ukuran 30: 15
(9, 9, 12, false),   -- Biru Jeans Ukuran 32: 12
(10, 10, 18, false),  -- Biru Jeans Ukuran 34: 18
(11, 11, 10, false),  -- Hitam Jeans Ukuran 30: 10
(12, 12, 8, false),   -- Hitam Jeans Ukuran 32: 8
(13, 13, 14, false),  -- Wash Gelap Jeans Ukuran 32: 14
(14, 14, 25, false),  -- Merah Sepatu Lari Ukuran 8: 25
(15, 15, 22, false),  -- Merah Sepatu Lari Ukuran 9: 22
(16, 16, 18, false),  -- Merah Sepatu Lari Ukuran 10: 18
(17, 17, 12, false),  -- Hitam Sepatu Lari Ukuran 8: 12
(18, 18, 15, false),  -- Hitam Sepatu Lari Ukuran 9: 15
(19, 19, 10, false),  -- Hitam Sepatu Lari Ukuran 10: 10
(20, 20, 16, false),  -- Putih Sepatu Lari Ukuran 9: 16
(21, 21, 30, false),  -- Hitam Tas Ransel Standar: 30
(22, 22, 28, false),  -- Biru Tas Ransel Standar: 28
(23, 23, 15, false),  -- Abu Tas Ransel Travel: 15
(24, 24, 20, false),  -- Hijau Tas Ransel Outdoor: 20
(25, 25, 8, false),   -- Tali Kulit Hitam Jam Tangan: 8
(26, 26, 6, false),   -- Tali Kulit Coklat Jam Tangan: 6
(27, 27, 5, false);   -- Tali Logam Silver Jam Tangan: 5