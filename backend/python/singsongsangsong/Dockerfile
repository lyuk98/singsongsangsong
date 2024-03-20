FROM python:3.11

WORKDIR /app
COPY . /app

RUN pip install fastapi uvicorn gunicorn

EXPOSE 8000

CMD ["gunicorn", "main:app", "--workers", "4", "--worker-class", "uvicorn.workers.UvicornWorker", "--bind", "0.0.0.0:8000"]
