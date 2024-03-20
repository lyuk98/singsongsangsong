import { ResponsiveRadar } from "@nivo/radar";

const data = [
  {
    taste: "fruity",
    chardonay: 40,
    carmenere: 109,
    syrah: 20,
  },
  {
    taste: "bitter",
    chardonay: 111,
    carmenere: 98,
    syrah: 78,
  },
  {
    taste: "heavy",
    chardonay: 79,
    carmenere: 47,
    syrah: 75,
  },
  {
    taste: "strong",
    chardonay: 33,
    carmenere: 78,
    syrah: 34,
  },
  {
    taste: "sunny",
    chardonay: 27,
    carmenere: 38,
    syrah: 55,
  },
];

const RaderChart = () => (
  <div style={{ width: "100%", height: "100%", margin: "0 auto" }}>
    <ResponsiveRadar
      data={data}
      keys={["chardonay", "carmenere", "syrah"]}
      indexBy="taste"
      valueFormat=" >-.2f"
      margin={{ top: 0, right: 70, bottom: 0, left: 70 }}
      borderColor={{ theme: "grid.line.stroke" }}
      gridLevels={4}
      gridShape="linear"
      gridLabelOffset={12}
      enableDots={false}
      colors={{ scheme: "purple_blue_green" }}
      fillOpacity={1}
      blendMode="multiply"
      motionConfig="wobbly"
      theme={{
        text: {
          fontSize: 18,
        },
      }}
    />
  </div>
);

export default RaderChart;
