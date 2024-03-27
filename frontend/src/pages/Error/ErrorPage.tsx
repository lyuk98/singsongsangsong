import React from "react";
import { useRouteError } from "react-router-dom";

const ErrorPage = () => {
  const error = useRouteError();

  return (
    <div>
      <h2>404 NOT FOUND</h2>
    </div>
  );
};

export default ErrorPage;
