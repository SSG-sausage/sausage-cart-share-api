DROP TABLE IF EXISTS `CART_SHARE`;
DROP TABLE IF EXISTS `CART_SHARE_MBR`;
DROP TABLE IF EXISTS `CART_SHARE_ITEM`;
DROP TABLE IF EXISTS `CART_SHARE_ORD`;
DROP TABLE IF EXISTS `CART_SHARE_ORD_ITEM`;

CREATE TABLE `CART_SHARE`
(
    `CART_SHARE_ID`   bigint       NOT NULL AUTO_INCREMENT,
    `MASTR_MBR_ID`    bigint       NOT NULL,
    `CART_SHARE_NM`   varchar(30)  NOT NULL,
    `EDIT_PSBL_YN`    boolean      NOT NULL,
    `CART_SHARE_ADDR` varchar(100) NOT NULL,
    `REG_DTS`         datetime     NOT NULL,
    `REGPE_ID`        bigint NULL,
    `MOD_DTS`         datetime     NOT NULL,
    `MODPE_ID`        bigint NULL,
    PRIMARY KEY (`CART_SHARE_ID`)
);

CREATE TABLE `CART_SHARE_MBR`
(
    `CARD_SHARE_MBR_ID` bigint      NOT NULL AUTO_INCREMENT,
    `MBR_ID`            bigint      NOT NULL,
    `CART_SHARE_ID`     bigint      NOT NULL,
    `PROG_STAT_CD`      varchar(30) NOT NULL,
    `REG_DTS`           datetime    NOT NULL,
    `REGPE_ID`          bigint NULL,
    `MOD_DTS`           datetime    NOT NULL,
    `MODPE_ID`          bigint NULL,
    PRIMARY KEY (`CARD_SHARE_MBR_ID`)
);

CREATE TABLE `CART_SHARE_ITEM`
(
    `CART_SHARE_ITEM_ID` bigint   NOT NULL AUTO_INCREMENT,
    `MBR_ID`             bigint NULL,
    `ITEM_ID`            bigint   NOT NULL,
    `CART_SHARE_ID`      bigint   NOT NULL,
    `COMM_YN`            boolean  NOT NULL,
    `ITEM_QTY`           int      NOT NULL,
    `REG_DTS`            datetime NOT NULL,
    `REGPE_ID`           bigint NULL,
    `MOD_DTS`            datetime NOT NULL,
    `MODPE_ID`           bigint NULL,
    PRIMARY KEY (`CART_SHARE_ITEM_ID`)
);

CREATE TABLE `CART_SHARE_ORD`
(
    `CART_SHARE_ORD_ID`      bigint   NOT NULL AUTO_INCREMENT,
    `CART_SHARE_ID`          bigint   NOT NULL,
    `CART_SHARE_ORD_RCP_DTS` datetime NOT NULL,
    `REG_DTS`                datetime NOT NULL,
    `REGPE_ID`               bigint NULL,
    `MOD_DTS`                datetime NOT NULL,
    `MODPE_ID`               bigint NULL,
    PRIMARY KEY (`CART_SHARE_ORD_ID`)
);

CREATE TABLE `CART_SHARE_ORD_ITEM`
(
    `CART_SHARE_ORD_ITEM_ID` bigint   NOT NULL AUTO_INCREMENT,
    `ITEM_ID`                bigint   NOT NULL,
    `CART_SHARE_ORD_ID`      bigint   NOT NULL,
    `MBR_ID`                 bigint   NOT NULL,
    `ITEM_QTY`               int      NOT NULL,
    `COM_YN`                 boolean  NOT NULL,
    `ITEM_AMT`               int      NOT NULL,
    `PAYMT_AMT`              int      NOT NULL,
    `REG_DTS`                datetime NOT NULL,
    `REGPE_ID`               bigint NULL,
    `MOD_DTS`                datetime NOT NULL,
    `MODPE_ID`               bigint NULL,
    PRIMARY KEY (`CART_SHARE_ORD_ITEM_ID`)
);