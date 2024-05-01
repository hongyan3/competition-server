import { defineStore } from 'pinia'
import type {UserVO} from "@/api";
import { userInfo } from 'os';

export const useStore = defineStore('auth', {
    state() {
        return {
            userInfo: {} as UserVO
        }
    },
    actions: {
        setUserInfo: (info: UserVO) => {
            this.userInfo = info
        }
    }
})