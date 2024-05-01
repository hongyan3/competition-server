/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { EntryVO } from './EntryVO';
import type { OrderItem } from './OrderItem';
export type Page_EntryVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: Array<OrderItem>;
    pages?: number;
    records?: Array<EntryVO>;
    searchCount?: boolean;
    size?: number;
    total?: number;
};

