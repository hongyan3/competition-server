/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { UserVO } from './UserVO';
export type EntryCommentVO = {
    commentType?: number;
    content?: string;
    createTime?: string;
    entryId?: number;
    id?: number;
    replyId?: number;
    replyList?: Array<EntryCommentVO>;
    updateTime?: string;
    userInfo?: UserVO;
};

