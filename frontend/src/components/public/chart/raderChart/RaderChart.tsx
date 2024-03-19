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
  <ResponsiveRadar
    data={data}
    keys={["chardonay", "carmenere", "syrah"]}
    indexBy="taste"
    valueFormat=" >-.2f"
    margin={{ top: 70, right: 60, bottom: 40, left: 60 }}
    borderColor={{ theme: "grid.line.stroke" }}
    gridLevels={4}
    gridShape="linear"
    gridLabelOffset={36}
    dotSize={5}
    dotColor="#22A2FF"
    dotBorderWidth={2}
    dotBorderColor={{ theme: "grid.line.stroke" }}
    colors={{ scheme: "purple_blue_green" }}
    fillOpacity={1}
    blendMode="multiply"
    motionConfig="wobbly"
    legends={[
      {
        anchor: "top-left",
        direction: "column",
        translateX: -50,
        translateY: -40,
        itemWidth: 80,
        itemHeight: 20,
        itemTextColor: "#999",
        symbolSize: 12,
        symbolShape: "circle",
        effects: [
          {
            on: "hover",
            style: {
              itemTextColor: "#000",
            },
          },
        ],
      },
    ]}
  />
);

export default RaderChart;
