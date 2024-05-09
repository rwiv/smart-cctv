import {mq} from "@/lib/style/mediaQueries.ts";
import {containerStyle, flexStyle} from "@/styles/globalStyles.ts";

const left = mq.m_all(2,2,3,3,3,3);
const right = mq.m_all(10,10,9, 9,9,9);

export function IndexPage() {

  return (
    <div css={containerStyle}>
      <div css={[left, flexStyle]}>
        hello
      </div>
      <div css={[right, flexStyle, {background: "#eeeeee"}]}>
        hello
      </div>
    </div>
  )
}
