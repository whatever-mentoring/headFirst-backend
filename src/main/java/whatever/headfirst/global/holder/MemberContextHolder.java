package whatever.headfirst.global.holder;

import whatever.headfirst.domain.member.domain.Member;

public class MemberContextHolder {
    private static final ThreadLocal<Member> memberThreadLocal = new ThreadLocal<>();

    public static void setMember(Member member) {
        memberThreadLocal.set(member);
    }

    public static Member getMember() {
        return memberThreadLocal.get();
    }

    public static void clear() {
        memberThreadLocal.remove();
    }
}