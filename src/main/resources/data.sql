INSERT INTO `CART_SHARE`
(CART_SHARE_ID, MASTR_MBR_ID, CART_SHARE_NM, EDIT_PSBL_YN, CART_SHARE_ADDR, REG_DTS, REGPE_ID,
 MOD_DTS, MODPE_ID)
VALUES (1, 1, "테스트 공유 장바구니 1", true, "테스트 배송지 1", "2023-01-01", 1, "2023-01-01", 1),
       (2, 1, "테스트 공유 장바구니 2", true, "테스트 배송지 2", "2023-01-01", 1, "2023-01-01", 1),
       (3, 1, "테스트 공유 장바구니 3", true, "테스트 배송지 3", "2023-01-01", 1, "2023-01-01", 1);

INSERT INTO `CART_SHARE_MBR`
(CARD_SHARE_MBR_ID, MBR_ID, CART_SHARE_ID, PROG_STAT_CD, REG_DTS, REGPE_ID, MOD_DTS, MODPE_ID)
VALUES (1, 1, 1, "NONE", "2023-01-01", 1, "2023-01-01", 1),
       (2, 2, 1, "NONE", "2023-01-01", 2, "2023-01-01", 2),
       (3, 3, 1, "NONE", "2023-01-01", 3, "2023-01-01", 3);

INSERT INTO `CART_SHARE_ITEM`
(CART_SHARE_ITEM_ID, MBR_ID, ITEM_ID, CART_SHARE_ID, COMM_YN, ITEM_QTY, REG_DTS, REGPE_ID, MOD_DTS,
 MODPE_ID)
VALUES (1, 1, 1, 1, false, 1, "2023-01-01", 1, "2023-01-01", 1),
       (2, 1, 2, 1, false, 2, "2023-01-01", 1, "2023-01-01", 1),
       (3, 2, 3, 1, false, 5, "2023-01-01", 2, "2023-01-01", 2),
       (4, 3, 4, 1, true, 10, "2023-01-01", 3, "2023-01-01", 3);